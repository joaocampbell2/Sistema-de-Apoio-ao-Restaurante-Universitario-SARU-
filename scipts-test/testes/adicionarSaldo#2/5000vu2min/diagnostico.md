### Análise do Teste

#### Verificações
- **Status 200**: 100% das verificações passaram (91276 de 91276), indicando que todas as requisições retornaram status 200.

#### Erros
- **Erros**: 0% de erros (0 de 91276), indicando que não houve falhas nas requisições.

#### Dados Transferidos
- **Dados Recebidos**: 20 MB (158 kB/s)
- **Dados Enviados**: 29 MB (229 kB/s)

#### Duração das Requisições (http_req_duration)
- **Média**: 2.47s
- **Mediana**: 2.61s
- **Máximo**: 7.37s
- **p(90)**: 5.24s
- **p(95)**: 5.88s

#### Tempo de Espera (http_req_waiting)
- **Média**: 2.47s
- **Mediana**: 2.61s
- **Máximo**: 7.37s
- **p(90)**: 5.24s
- **p(95)**: 5.88s

#### Requisições por Segundo (http_reqs)
- **Total**: 91276 requisições
- **Taxa**: 719.989543/s

#### Duração das Iterações (iteration_duration)
- **Média**: 3.47s
- **Mediana**: 3.61s
- **Máximo**: 8.37s
- **p(90)**: 6.24s
- **p(95)**: 6.88s

#### Concorrência
- **Máximo de Usuários Virtuais (vus_max)**: 5000 

### Conclusão
Com base nos resultados:

- **Latência**: A latência média (2.47s) e a mediana (2.61s) são altas, indicando que o sistema está demorando mais para responder sob carga. O valor máximo de 7.37s e os percentis p(90) = 5.24s e p(95) = 5.88s indicam que há picos significativos de latência.
- **Vazão**: A taxa de requisições por segundo (719.989543/s) é alta e consistente, e não houve falhas nas requisições.
- **Concorrência**: O teste foi executado com até 5000 usuários virtuais, e todas as iterações foram completadas sem interrupções.
