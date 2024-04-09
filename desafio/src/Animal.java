public class Animal {
    private String id;
    private double altura;
    private double comprimento;
    private double largura;
    private double peso;
    private String tipo;
    private String status;
    private String dataVenda;
    private String dataPerda;

    public Animal(String id, double altura, double comprimento, double largura, double peso, String tipo) {
        this.id = id;
        this.altura = altura;
        this.comprimento = comprimento;
        this.largura = largura;
        this.peso = peso;
        this.tipo = tipo;
        this.status = "Ativo";
    }

    public String getDataVenda(){
        return dataVenda;
    }
    public void setDataVenda(String venda){
        this.dataVenda = venda;
    }

    public String getDataPerda(){
        return dataPerda;
    }

    public void setDataPerda(String perda){
        this.dataPerda = perda;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        if (altura > 0) {
            this.altura = altura;
        } else {
            System.out.println("Altura inválida.");
        }
    }

    public double getComprimento() {
        return comprimento;
    }

    public void setComprimento(double comprimento) {
        if (comprimento > 0) {
            this.comprimento = comprimento;
        } else {
            System.out.println("Comprimento inválido.");
        }
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        if (largura > 0) {
            this.largura = largura;
        } else {
            System.out.println("Largura inválida.");
        }
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        if (peso > 0) {
            this.peso = peso;
        } else {
            System.out.println("Peso inválido.");
        }
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
