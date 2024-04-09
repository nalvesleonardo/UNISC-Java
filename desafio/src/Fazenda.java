import java.util.HashMap;
import java.util.Map;

public class Fazenda {
    private Map<String, Animal> plantel;
    private Map<String, Double> vendas;
    private Map<String, Double> perdas;

    public Fazenda() {
        this.plantel = new HashMap<>();
        this.vendas = new HashMap<>();
        this.perdas = new HashMap<>();
    }

    public void incluirAnimal(Animal animal) {
        plantel.put(animal.getId(), animal);
    }

    public Animal consultarAnimal(String id) {
        return plantel.get(id);
    }

    public void avaliarAnimal(String id, double altura, double comprimento, double largura, double peso) {
        Animal animal = plantel.get(id);
        if (altura >= animal.getAltura() && comprimento >= animal.getComprimento() && largura >= animal.getLargura() && peso >= animal.getPeso()) {
            animal.setAltura(altura);
            animal.setComprimento(comprimento);
            animal.setLargura(largura);
            animal.setPeso(peso);
        } else {
            System.out.println("Novas medidas são menores que as anteriores. Consulte um veterinário.");
        }
    }

    public void registrarVenda(String id, double valor, String data) {
        Animal animal = plantel.get(id);
        animal.setStatus("Vendido");
        animal.setDataVenda(data);
        vendas.put(id, valor);
    }

    public void registrarPerda(String id, String perda) {
        Animal animal = plantel.get(id);
        animal.setStatus("Perdido");
        animal.setDataPerda(perda);
        perdas.put(id, animal.getPeso()); 
    }

    public void relatorioAnimaisPorTipo(String tipo) {
        boolean encontrado = false;
        System.out.println("\nRelatório de Animais Ativos do Tipo " + tipo + ":");
        for (Animal animal : plantel.values()) {
            if (animal.getTipo().equals(tipo) && animal.getStatus().equals("Ativo")) {
                encontrado = true;
                System.out.println(animal.getId() + ": Altura: " + animal.getAltura() + " cm, Comprimento: " +
                        animal.getComprimento() + " cm, Largura: " + animal.getLargura() + " cm, Peso: " + animal.getPeso()+" kg.");
            }
        }        
        if (!encontrado) {
            System.out.println("Nenhum animal ativo do tipo " + tipo + " encontrado.");
        }
    }

    public void relatorioVendas() {
        System.out.println("\nRelatório de Vendas:");
        double totalVendas = 0;
        if (vendas.isEmpty()) {
            System.out.println("Ainda não houveram vendas");
        }
        else{
            for (Map.Entry<String, Double> entry : vendas.entrySet()) {
                String id = entry.getKey();
                double valor = entry.getValue();
                Animal animal = plantel.get(id);
                System.out.println("Animal " + id + ", Tipo " + animal.getTipo() + ", Valor da Venda: " + valor + " reais, Data da venda: " +animal.getDataVenda());
                totalVendas += valor;
            }
        System.out.println("Total de Vendas: " + totalVendas);
        }
    }

    public void relatorioPerdas() {
        System.out.println("\nRelatório de Perdas:");
        if (perdas.isEmpty()) {
            System.out.println("Não houve perdas.");
            return;
        }

        System.out.println("\nRelatório de Perdas:");
        Map<String, Integer> contagemPerdasPorTipo = new HashMap<>();
        for (Map.Entry<String, Double> entry : perdas.entrySet()) {
            String id = entry.getKey();
            Animal animal = plantel.get(id);
            String tipo = animal.getTipo();
            System.out.println("Animal " + id + ", Tipo " + tipo + ", Peso " + entry.getValue()+" kg, Data da perda: " + animal.getDataPerda());
            contagemPerdasPorTipo.put(tipo, contagemPerdasPorTipo.getOrDefault(tipo, 0) + 1);
        }
        System.out.println("Contagem de Perdas por Tipo:");
        for (Map.Entry<String, Integer> entry : contagemPerdasPorTipo.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public boolean existeAnimalComId(String id) {
        return plantel.containsKey(id);
    }
    
}