import http from 'k6/http';
import { check, sleep } from 'k6';
import { Rate } from 'k6/metrics';


export let errorRate = new Rate('errors');

export let options = {
    stages: [
//        { duration: '2m', target: 100 }
//        { duration: '2m', target: 500 }
//        { duration: '2m', target: 1000 }
//        { duration: '2m', target: 2000 }
//        { duration: '2m', target: 3000 }
//        { duration: '2m', target: 4000 }
//        { duration: '2m', target: 5000 }
    ],
    thresholds: {
        http_req_duration: ['p(95)<500'],
        errors: ['rate<0.01'],
    },
};

export default function () {
    let url = 'http://localhost:8080/cliente/adicionarSaldo';
    let payload = JSON.stringify({
        valor: 1
    });

    let params = {
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxODk1NTYyNTcwNyIsInJvbGUiOiJBTFVOTyJ9.94NJH2gRqmgmf7g8OUINTBZEY1s4532WHKwXnN0O7iI',
        },
    };


    let res = http.put(url, payload, params);


    check(res, {
        'is status 200': (r) => r.status === 200,
    }) || errorRate.add(1);

    sleep(1);
}