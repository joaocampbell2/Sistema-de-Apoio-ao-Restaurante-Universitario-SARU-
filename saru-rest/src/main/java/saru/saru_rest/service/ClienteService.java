package saru.saru_rest.service;

import org.springframework.stereotype.Service;
import saru.saru_rest.entity.ClienteEntity;
import saru.saru_rest.exceptions.CpfInexistenteException;
import saru.saru_rest.exceptions.NaoFoiPossivelAdicionarSaldo;
import saru.saru_rest.exceptions.SaldoExcedenteException;
import saru.saru_rest.repository.ClienteRepository;

import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public String adicionarSaldo(String cpf, float valor)
    {
            try{
                Optional<ClienteEntity> cliente = clienteRepository.findById(cpf);
                if(cliente.isPresent() && verificaSaldoExcedente(cpf, valor)){
                    cliente.get().setSaldo(valor + cliente.get().getSaldo());
                    clienteRepository.save(cliente.get());
                    return "Saldo adicionado";
                }
            }catch(Exception exception){
                throw new NaoFoiPossivelAdicionarSaldo();
            }
            return null;
    }
    public boolean verificaSaldoExcedente(String cpf, float valor) throws SaldoExcedenteException, CpfInexistenteException {

        Optional<ClienteEntity> cliente = clienteRepository.findById(cpf);
        if (cliente.isPresent()){
            float saldo = cliente.get().getSaldo();
            if(saldo >= 500 || (saldo + valor) > 500){
                throw new SaldoExcedenteException();
            }
            return true;
        }
        else {
            throw new CpfInexistenteException(cpf);
        }
    }
    
}
