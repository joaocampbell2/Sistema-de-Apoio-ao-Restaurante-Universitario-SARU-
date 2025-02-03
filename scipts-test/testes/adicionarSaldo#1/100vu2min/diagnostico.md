### Análise do Teste

#### Verificações
- **Status 200**: 100% das verificações passaram (6021 de 6021), indicando que todas as requisições retornaram status 200.

#### Erros
- **Erros**: 0% de erros (0 de 6021), indicando que não houve falhas nas requisições.

#### Dados Transferidos
- **Dados Recebidos**: 1.3 MB (11 kB/s)
- **Dados Enviados**: 1.9 MB (16 kB/s)

#### Duração das Requisições (http_req_duration)
- **Média**: 4.28ms
- **Mediana**: 4.16ms
- **Máximo**: 14.24ms
- **p(90)**: 4.91ms
- **p(95)**: 5.27ms

#### Tempo de Espera (http_req_waiting)
- **Média**: 3.85ms
- **Mediana**: 3.8ms
- **Máximo**: 13.9ms
- **p(90)**: 4.58ms
- **p(95)**: 4.93ms

#### Requisições por Segundo (http_reqs)
- **Total**: 6021 requisições
- **Taxa**: 49.765474/s

#### Duração das Iterações (iteration_duration)
- **Média**: 1s
- **Mediana**: 1s
- **Máximo**: 1.01s
- **p(90)**: 1s
- **p(95)**: 1s

#### Concorrência
- **Máximo de Usuários Virtuais (vus_max)**: 100 

### Conclusão
Com base nos resultados:

- **Latência**: A latência média (4.28ms) e a mediana (4.16ms) são baixas, indicando que o sistema responde rapidamente na maioria das vezes. O valor máximo de 14.24ms é aceitável e não indica gargalos significativos.
- **Vazão**: A taxa de requisições por segundo (49.765474/s) é consistente, e não houve falhas nas requisições.
- **Concorrência**: O teste foi executado com até 100 usuários virtuais, e todas as iterações foram completadas sem interrupções.