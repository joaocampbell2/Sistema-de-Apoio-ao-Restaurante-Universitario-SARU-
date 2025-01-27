package saru.saru_rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import saru.saru_rest.entity.LogEntity;

public class LogDTO {
    @JsonProperty(value = "_id")
        private String id;  // Representa o campo "_id" do MongoDB
        @JsonProperty(value = "usuario_info")
        private LogEntity.UsuarioInfo usuarioInfo;
        @JsonProperty(value = "acao")
        private LogEntity.Acao acao;
        @JsonProperty(value = "detalhes")
        private LogEntity.Detalhes detalhes;


    public LogDTO(LogEntity log) {
        this.detalhes = log.getDetalhes();
        this.acao = log.getAcao();
        this.usuarioInfo = log.getUsuarioInfo();
        this.id = log.getId();
    }


        public static class UsuarioInfo {
            @JsonProperty(value="cpf")
            private String cpf;
            @JsonProperty(value="tipo_usuario")
            private String tipoUsuario;

            public UsuarioInfo(LogEntity.UsuarioInfo usuarioInfo) {
                this.tipoUsuario = usuarioInfo.getTipoUsuario();
                this.cpf = usuarioInfo.getCpf();
            }

            @Override
            public String toString() {
                return "UsuarioInfo{" +
                        "cpf='" + cpf + '\'' +
                        ", tipoUsuario='" + tipoUsuario + '\'' +
                        '}';
            }
        }

        public static class Acao {
            @JsonProperty(value="descricao")

            private String descricao;
            @JsonProperty(value= "status")
            private String status;

            public Acao(LogEntity.Acao acao) {
                this.descricao = acao.getDescricao();
                this.status = acao.getStatus();
            }


            @Override
            public String toString() {
                return "Acao{" +
                        "descricao='" + descricao + '\'' +
                        ", status='" + status + '\'' +
                        '}';
            }
        }

        public static class Detalhes {
            @JsonProperty(value="ip_origem")
            private String ipOrigem;
            @JsonProperty(value="device")
            private String device;

            public Detalhes(Detalhes detalhes) {
                this.device = detalhes.device;
                this.ipOrigem = detalhes.ipOrigem;
            }

            @Override
            public String toString() {
                return "Detalhes{" +
                        "ipOrigem='" + ipOrigem + '\'' +
                        ", device='" + device + '\'' +
                        '}';
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
}
