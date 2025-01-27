package saru.saru_rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import saru.saru_rest.entity.ClienteEntity;
import saru.saru_rest.exceptions.CpfInexistenteException;
import saru.saru_rest.exceptions.NaoFoiPossivelAdicionarSaldo;
import saru.saru_rest.exceptions.SaldoExcedenteException;
import saru.saru_rest.repository.ClienteRepository;

import java.util.Optional;

@Service
public class ClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public String adicionarSaldo(String cpf, float valor) {
        logger.info("Iniciando processo de adição de saldo de valor {} para o cliente com CPF {}", valor, cpf);
        try {
            Optional<ClienteEntity> cliente = clienteRepository.findById(cpf);
            if(cliente.isPresent() && verificaSaldoExcedente(cpf, valor)){
                cliente.get().setSaldo(valor + cliente.get().getSaldo());
                clienteRepository.save(cliente.get());
                logger.info("Saldo de {} adicionado com sucesso para o cliente com CPF {}", valor, cpf);
                return "Saldo adicionado";
            }
        } catch (Exception exception) {
            logger.error("Erro ao adicionar saldo de valor {} para o cliente com CPF {}: {}", valor, cpf, exception.getMessage());
            throw new NaoFoiPossivelAdicionarSaldo();
        }
        logger.warn("Não foi possível adicionar saldo para o cliente com CPF {}", cpf);
        return null;
    }

    public boolean verificaSaldoExcedente(String cpf, float valor) throws SaldoExcedenteException, CpfInexistenteException {
        logger.info("Verificando se o cliente com CPF {} tem saldo excedente para adicionar valor {}", cpf, valor);
        Optional<ClienteEntity> cliente = clienteRepository.findById(cpf);
        if (cliente.isPresent()){
            float saldo = cliente.get().getSaldo();
            if(saldo >= 500 || (saldo + valor) > 500){
                logger.warn("Saldo excedente detectado para o cliente com CPF {}. Saldo atual: {}, tentativa de adição: {}", cpf, saldo, valor);
                throw new SaldoExcedenteException();
            }
            logger.info("Cliente com CPF {} pode adicionar o valor de {} ao seu saldo atual de {}", cpf, valor, saldo);
            return true;
        } else {
            logger.error("Cliente com CPF {} não encontrado.", cpf);
            throw new CpfInexistenteException(cpf);
        }
    }

    public float getSaldo(String cpf) throws CpfInexistenteException {
        logger.info("Consultando saldo do cliente com CPF {}", cpf);
        Optional<ClienteEntity> cliente = clienteRepository.findById(cpf);
        if(cliente.isPresent()){
            logger.info("Saldo encontrado para o cliente com CPF {}: {}", cpf, cliente.get().getSaldo());
            return cliente.get().getSaldo();
        } else {
            logger.error("Cliente com CPF {} não encontrado.", cpf);
            throw new CpfInexistenteException(cpf);
        }
    }
}
