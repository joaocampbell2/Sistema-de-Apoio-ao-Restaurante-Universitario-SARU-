### Análise do Teste

#### Verificações
- **Status 200**: 100% das verificações passaram (87219 de 87219), indicando que todas as requisições retornaram status 200.

#### Erros
- **Erros**: 0% de erros (0 de 87219), indicando que não houve falhas nas requisições.

#### Dados Transferidos
- **Dados Recebidos**: 19 MB (156 kB/s)
- **Dados Enviados**: 28 MB (227 kB/s)

#### Duração das Requisições (http_req_duration)
- **Média**: 400.28ms
- **Mediana**: 263.62ms
- **Máximo**: 2.92s
- **p(90)**: 1.06s
- **p(95)**: 1.22s

#### Tempo de Espera (http_req_waiting)
- **Média**: 400ms
- **Mediana**: 263.48ms
- **Máximo**: 2.92s
- **p(90)**: 1.05s
- **p(95)**: 1.22s

#### Requisições por Segundo (http_reqs)
- **Total**: 87219 requisições
- **Taxa**: 713.89655/s

#### Duração das Iterações (iteration_duration)
- **Média**: 1.4s
- **Mediana**: 1.26s
- **Máximo**: 3.93s
- **p(90)**: 2.06s
- **p(95)**: 2.22s

#### Concorrência
- **Máximo de Usuários Virtuais (vus_max)**: 2000 

### Conclusão
Com base nos resultados:

- **Latência**: A latência média (400.28ms) e a mediana (263.62ms) são altas, indicando que o sistema está demorando para responder. O valor máximo de 2.92s é muito alto e indica gargalos significativos.
- **Vazão**: A taxa de requisições por segundo (713.89655/s) é alta, mas a latência elevada sugere que o sistema está sobrecarregado.
- **Concorrência**: O teste foi executado com até 2000 usuários virtuais, e todas as iterações foram completadas sem interrupções.

#### Problema Identificado
- **Threshold Excedido**: Os thresholds definidos para `http_req_duration` foram excedidos, indicando que a duração das requisições está acima do limite aceitável (`p(95)<500`).

### Recomendações
- **Otimização de Desempenho**: Investigar e otimizar os pontos de gargalo no sistema para reduzir a latência das requisições.
- **Escalabilidade**: Considerar melhorias na infraestrutura para suportar melhor a carga de usuários virtuais.