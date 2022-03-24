package tech.noetzold.Apidarlan.contato.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Experiencia {

        @Id
        @GeneratedValue(generator = "increment")
        @GenericGenerator(name = "increment", strategy = "increment")
        private Long id;

        @NotBlank
        private String nome;

        private int tempoMeses;

        @ManyToOne
        Especialidade especialidade;

        @NotNull
        private TiposDeTecnologia tiposDeTecnologia;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public Especialidade getEspecialidade() {
            return especialidade;
        }

        public void setEspecialidade(Especialidade especialidade) {
            this.especialidade = especialidade;
        }

        public int getTempoMeses() {
            return tempoMeses;
        }

        public void setTempoMeses(int tempoMeses) {
            this.tempoMeses = tempoMeses;
        }

    public TiposDeTecnologia getTiposDeTecnologia() {
        return tiposDeTecnologia;
    }

    public void setTiposDeTecnologia(TiposDeTecnologia tiposDeTecnologia) {
        this.tiposDeTecnologia = tiposDeTecnologia;
    }

    @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((id == null) ? 0 : id.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Experiencia other = (Experiencia) obj;
            if (id == null) {
                if (other.id != null)
                    return false;
            } else if (!id.equals(other.id))
                return false;
            return true;
        }
    }

