export class LoginDto{
    private readonly cpf: string
    private readonly senha: string
    constructor(cpf: string, senha:string){
        this.cpf = cpf;
        this.senha = senha;
    }

}