package entity;

public class Sala {
    private Integer id;
    private String nome;
    private Integer IdSede;


    public Sala() {
    }

    public Sala(Integer id, String nome, Integer IdSede) {
        this.id = id;
        this.nome = nome;
        this.IdSede = IdSede;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdSede() {
        return IdSede;
    }

    public void setIdSede(Integer idSede) {
        IdSede = idSede;
    }
}
