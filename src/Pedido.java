public class Pedido {
    private int numPedido = 0;
    private float distancia;
    private String endereco;
    private String tamanhoPizza[];
    private String saborPizza[];

    public Pedido(int tam) {
        tamanhoPizza = new String[tam];
        saborPizza = new String[tam];
    }

    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {

        this.numPedido = numPedido;
    }

    public float getDistancia() {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTamanhoPizza(int indice) {
        return tamanhoPizza[indice];
    }

    public String[] getTamanhosPizzas() {
        return tamanhoPizza;
    }

    public void setTamanhoPizza(int index, String tamanhoPizza) {
        this.tamanhoPizza[index] = tamanhoPizza;
    }

    public void setTamanhosPizzas(String[] tamanhosPizzas) {
        this.tamanhoPizza = tamanhosPizzas;
    }

    public String[] getSaboresPizzas() {
        return saborPizza;
    }

    public String getSaborPizza(int indice) {
        return saborPizza[indice];
    }

    public void setSaborPizza(int indice, String saborPizza) {
        this.saborPizza[indice] = saborPizza;
    }

    public void setSaboresPizzas(String[] saboresPizzas) {
        this.saborPizza = saboresPizzas;
    }
}
