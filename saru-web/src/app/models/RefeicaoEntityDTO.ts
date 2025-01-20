export interface RefeicaoEntityDTO {
    idRefeicao: number;
    cpfCliente: string;
    data: Date;
    turno: string;
    token: string;
    utilizado: boolean;
}