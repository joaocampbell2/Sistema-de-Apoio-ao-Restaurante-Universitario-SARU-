package saru.saru_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saru.saru_rest.entity.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {
    default String addSaldo(String cpf, float valor) {
        float saldo = this.getById(cpf).getSaldo();
            saldo = saldo + valor;
            ClienteEntity cliente = this.getById(cpf);
            cliente.setSaldo(saldo);
            this.save(cliente);
            return "Saldo adicionado";
        }
    
    
}
