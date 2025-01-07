package saru.saru_rest.service;

import org.springframework.stereotype.Service;
import saru.saru_rest.exceptions.SaldoExcedenteException;
import saru.saru_rest.repository.ClienteRepository;
@Service
public class AdicionarSaldoService {
    private ClienteRepository clienteRepository;

    public AdicionarSaldoService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    private boolean receberPagamento(float valor){
        //Não haverá pagamento real nesta versão, para fins de demonstração e por limitações
        
        return true;
    }
    public String adicionarSaldo(String cpf, float valor)
    {
        float saldo = clienteRepository.findById(cpf).get().getSaldo();
            try{
                if(clienteRepository.existsById(cpf) && this.receberPagamento(valor) && verificaSaldoExcedente(cpf, valor)){
                    return clienteRepository.addSaldo(cpf, valor);
                }
            }catch(Exception exception){
                System.out.println("Erro ao adicionar saldo");
            }
        return "Erro ao adicionar saldo";
    }
    public boolean verificaSaldoExcedente(String cpf, float valor) throws SaldoExcedenteException{
        float saldo = clienteRepository.findById(cpf).get().getSaldo();
        if(saldo >= 500 || (saldo + valor) > 500){
            throw new SaldoExcedenteException();
        }
        return true;
    }
    
}
