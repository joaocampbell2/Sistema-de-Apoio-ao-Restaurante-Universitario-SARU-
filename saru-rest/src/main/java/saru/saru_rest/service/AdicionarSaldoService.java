package saru.saru_rest.service;

import saru.saru_rest.repository.ClienteRepository;

public class AdicionarSaldoService {
    private ClienteRepository clienteRepository;
    public String adicionarSaldo(String id)
    {
        
        try{
            clienteRepository.existsById("Oi");
        }catch(Exception exception){
               
        }
        return null;
    }
}
