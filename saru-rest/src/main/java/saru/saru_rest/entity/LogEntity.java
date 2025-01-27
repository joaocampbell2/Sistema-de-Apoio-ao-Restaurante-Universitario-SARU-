package saru.saru_rest.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "logs")
public class LogEntity {

    private String id;
    @Field(name = "usuario_info")
    private UsuarioInfo usuarioInfo;
    private Acao acao;
    private Detalhes detalhes;

    public LogEntity(String id, String cpf, String tipoUsuario,String descricao, String status , String ipOrigem, String device) {
        this.id = id;
        this.usuarioInfo = new UsuarioInfo(cpf,tipoUsuario);
        this.acao = new Acao(descricao,status);
        this.detalhes = new Detalhes(ipOrigem,device);
    }

    @Data
    public static class UsuarioInfo {
        private String cpf;
        @Field(name="tipo_usuario")
        private String tipoUsuario;

        public UsuarioInfo(String cpf, String tipoUsuario) {
            this.cpf = cpf;
            this.tipoUsuario = tipoUsuario;
        }

        @Override
        public String toString() {
            return "UsuarioInfo{" +
                    "cpf='" + cpf + '\'' +
                    ", tipoUsuario='" + tipoUsuario + '\'' +
                    '}';
        }

        public String getCpf() {
            return cpf;
        }

        public String getTipoUsuario() {
            return tipoUsuario;
        }
    }

    @Data
    public static class Acao {

        public Acao(String descricao, String status) {
            this.descricao = descricao;
            this.status = status;
        }

        private String descricao;
        private String status;

        @Override
        public String toString() {
            return "Acao{" +
                    "descricao='" + descricao + '\'' +
                    ", status='" + status + '\'' +
                    '}';
        }

        public String getDescricao() {
            return descricao;
        }

        public String getStatus() {
            return status;
        }
    }

    @Data
    public static class Detalhes {

        public Detalhes(String ipOrigem, String device) {
            this.ipOrigem = ipOrigem;
            this.device = device;
        }

        @Field(name="ip_origem")
        private String ipOrigem;
        private String device;

        @Override
        public String toString() {
            return "Detalhes{" +
                    "ipOrigem='" + ipOrigem + '\'' +
                    ", device='" + device + '\'' +
                    '}';
        }

        public String getIpOrigem() {
            return ipOrigem;
        }

        public String getDevice() {
            return device;
        }
    }

    @Override
    public String toString() {
        return "Log{" +
                "id='" + id + '\'' +
                ", usuarioInfo=" + usuarioInfo +
                ", acao=" + acao +
                ", detalhes=" + detalhes +
                '}';
    }

    public String getId() {
        return id;
    }

    public UsuarioInfo getUsuarioInfo() {
        return usuarioInfo;
    }

    public Acao getAcao() {
        return acao;
    }

    public Detalhes getDetalhes() {
        return detalhes;
    }
}
