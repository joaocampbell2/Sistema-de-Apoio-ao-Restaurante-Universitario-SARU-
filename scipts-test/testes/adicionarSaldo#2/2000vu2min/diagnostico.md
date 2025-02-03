### Análise do Teste

#### Verificações
- **Status 200**: 100% das verificações passaram (78633 de 78633), indicando que todas as requisições retornaram status 200.

#### Erros
- **Erros**: 0% de erros (0 de 78633), indicando que não houve falhas nas requisições.

#### Dados Transferidos
- **Dados Recebidos**: 17 MB (141 kB/s)
- **Dados Enviados**: 25 MB (204 kB/s)

#### Duração das Requisições (http_req_duration)
- **Média**: 554.64ms
- **Mediana**: 452.8ms
- **Máximo**: 2.98s
- **p(90)**: 1.31s
- **p(95)**: 1.44s

#### Tempo de Espera (http_req_waiting)
- **Média**: 554.35ms
- **Mediana**: 452.54ms
- **Máximo**: 2.98s
- **p(90)**: 1.31s
- **p(95)**: 1.44s

#### Requisições por Segundo (http_reqs)
- **Total**: 78633 requisições
- **Taxa**: 642.640481/s

#### Duração das Iterações (iteration_duration)
- **Média**: 1.55s
- **Mediana**: 1.45s
- **Máximo**: 3.99s
- **p(90)**: 2.31s
- **p(95)**: 2.44s

#### Concorrência
- **Máximo de Usuários Virtuais (vus_max)**: 2000 

### Conclusão
Com base nos resultados:

- **Latência**: A latência média (554.64ms) e a mediana (452.8ms) são altas, indicando que o sistema está demorando mais para responder sob carga. O valor máximo de 2.98s e os percentis p(90) = 1.31s e p(95) = 1.44s indicam que há picos significativos de latência.
- **Vazão**: A taxa de requisições por segundo (642.640481/s) é alta e consistente, e não houve falhas nas requisições.
- **Concorrência**: O teste foi executado com até 2000 usuários virtuais, e todas as iterações foram completadas sem interrupções.


