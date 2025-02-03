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

    private final LogService logService;

    public ClienteService(ClienteRepository clienteRepository, LogService logService) {
        this.clienteRepository = clienteRepository;
        this.logService = logService;
    }

    public String adicionarSaldo(String cpf, float valor) {
        logger.info("Iniciando processo de adição de saldo de valor {} para o cliente com CPF {}", valor, cpf);
        try {
            Optional<ClienteEntity> cliente = clienteRepository.findById(cpf);
            if(cliente.isPresent() && verificaSaldoExcedente(cpf, valor, cliente)){
                cliente.get().setSaldo(valor + cliente.get().getSaldo());
                clienteRepository.save(cliente.get());
                logger.info("Saldo de {} adicionado com sucesso para o cliente com CPF {}", valor, cpf);
                logService.criarLog(cpf,"cliente","Saldo adicionado:" + valor,"sucess","","Computador");

                return "Saldo adicionado";
            }
        } catch (Exception exception) {
            logger.error("Erro ao adicionar saldo de valor {} para o cliente com CPF {}: {}", valor, cpf, exception.getMessage());
            logService.criarLog(cpf,"cliente","Erro ao adicionar saldo","error","","Computador");

            throw new NaoFoiPossivelAdicionarSaldo();
        }
        logger.warn("Não foi possível adicionar saldo para o cliente com CPF {}", cpf);
        logService.criarLog(cpf,"cliente","Erro ao adicionar saldo","error","","Computador");
        return null;
    }

    public boolean verificaSaldoExcedente(String cpf, float valor, Optional<ClienteEntity> cliente) throws SaldoExcedenteException, CpfInexistenteException {
        logger.info("Verificando se o cliente com CPF {} tem saldo excedente para adicionar valor {}", cpf, valor);
        if (cliente.isPresent()){
            float saldo = cliente.get().getSaldo();
            if(saldo >= 500000000 || (saldo + valor) > 500000000){ // ALTERADO TEMPORIARAMENTE PARA TESTES DE CARGA //
                logger.warn("Saldo excedente detectado para o cliente com CPF {}. Saldo atual: {}, tentativa de adição: {}", cpf, saldo, valor);
                logService.criarLog(cpf,"cliente","Erro ao adicionar saldo","error","","Computador");
                throw new SaldoExcedenteException();
            }
            logger.info("Cliente com CPF {} pode adicionar o valor de {} ao seu saldo atual de {}", cpf, valor, saldo);
            return true;
        } else {
            logger.error("Cliente com CPF {} não encontrado.", cpf);
            logService.criarLog(cpf,"cliente","Erro ao adicionar saldo","error","","Computador");
            throw new CpfInexistenteException(cpf);
        }
    }

    public float getSaldo(String cpf) throws CpfInexistenteException {
        logger.info("Consultando saldo do cliente com CPF {}", cpf);
        Optional<ClienteEntity> cliente = clienteRepository.findById(cpf);
        if(cliente.isPresent()){
            logger.info("Saldo encontrado para o cliente com CPF {}: {}", cpf, cliente.get().getSaldo());
            logService.criarLog(cpf,"cliente","Verificar saldo","sucess","","Computador");

            return cliente.get().getSaldo();
        } else {
            logger.error("Cliente com CPF {} não encontrado.", cpf);
            logService.criarLog(cpf,"cliente","Erro ao verificar saldo","error","","Computador");

            throw new CpfInexistenteException(cpf);
        }
    }
}
