import http from 'k6/http';
import { check, sleep } from 'k6';
import { Rate } from 'k6/metrics';

// Define a taxa de erro personalizada
export let errorRate = new Rate('errors');

// Configurações do teste de carga
export let options = {
    stages: [
//        { duration: '2m', target: 100 }
//       { duration: '5m', target: 500 }
//        { duration: '2m', target: 500 }
//        { duration: '5m', target: 1000 }
//        { duration: '2m', target: 1000 }
//        { duration: '2m', target: 0 }
//        { duration: '2m', target: 2000 }
//        { duration: '2m', target: 5000 }
//        { duration: '2m', target: 3000 }
        { duration: '2m', target: 4000 }
      ],
    thresholds: {
        http_req_duration: ['p(95)<500'],
        errors: ['rate<0.01'],
    },
};

// Função principal que será executada por cada usuário virtual
export default function () {
    let url = 'http://localhost:8080/auth/login';
    let payload = JSON.stringify({
        cpf: '18955625707',
        senha: '1234',
    });

    let params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    // Envia a requisição POST
    let res = http.post(url, payload, params);

    // Verifica se a resposta tem status 200
    check(res, {
        'is status 200': (r) => r.status === 200,
    }) || errorRate.add(1); // Adiciona à taxa de erro se a verificação falhar

    // Pausa por 1 segundo entre as requisições
     sleep(1);
}