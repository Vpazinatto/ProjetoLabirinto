package projetolabirinto;

import java.lang.reflect.*;

/**
 * Esta classe consiste em métodos para armazenar dados em uma lista, na forma de Fila.
 *
* @author Vinicius Pazinatto
* @author Daniel Carvalho de Moura
* 
* @param <X> tipo dos dados que devem ser armazenados
 */
public class Fila <X> implements Cloneable
{
    private ListaDesordenada<X> lista;

    /**
     * Método para clonar dados para armazenar ou retornar.
     *
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura
     * 
     * @param x dado para clonar
     */
    private X meuCloneDeX (X x)
    {
        X ret=null;
        
        try
        {
            Class<?> classe = x.getClass();
            Class<?>[] tipoParametroFormal = null; // null pq clone tem 0 parametros
            Method metodo = classe.getMethod ("clone", tipoParametroFormal);
            Object[] parametroReal = null; // null pq clone tem 0 parametros
            ret = (X)metodo.invoke (x, parametroReal);
        }
        catch (Exception erro)
        {}

        return ret;
    }

    /**
     * Construtor, instancia a Fila.
     *
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura
     * 
     * @throws Exception se houver algum erro ao instânciar a fila.
     */
    public Fila () throws Exception
    {
        this.lista = new ListaDesordenada<X> ();
    }

    /**
     * GuardeUmItem, método para armazenar dados na lista dentro da fila.
     * 
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura
     *
     * @param x Dado que deve ser armazenado.
     * @throws Exception se o parâmetro for nulo.
     */
    public void guardeUmItem (X x) throws Exception
    {
        if (x == null)
            throw new Exception ("Item inválido!");
        
        X info = null;
        
        if (x instanceof Cloneable)
            info = this.meuCloneDeX(x);
        else
            info = x;
        
        this.lista.insiraNoFinal(info);
    }

    /**
     * Getter responsável por retornar um item da fila
     * 
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura
     * 
     * @return retorna o primeiro item da fila.
     * @throws Exception
     */
    public X getUmItem () throws Exception
    {
        if (this.lista.vazia())
            throw new Exception ("Lista vazia!");
        
        X ret = null;
        X primeiro = this.lista.getPrimeiro();
        
        if (primeiro instanceof Cloneable)
            ret = this.meuCloneDeX(primeiro);
        else
            ret = primeiro;
        
        return ret;
    }

    /**
     * JogueUmItemFora, método responsável por remover itens da fila.
     * 
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura
     *
     * @throws Exception se a fila estiver vazia.
     */
    public void jogueUmItemFora () throws Exception
    {        
       this.lista.removePrimeiro();
    }

    /**
     * Vazia, método que informa se a fila está vazia ou não.
     * 
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura
     *
     * @return retorna true se a fila estiver vazia, e false se não estiver vazia.
     */
    public boolean vazia ()
    {
        if (this.lista.vazia())
            return true;
        else
            return false;
    }
    
    /**
     * ToString, retorna uma lista de nós com suas respectivas informações armazenadas.
     * 
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura
     *
     * @return retorna uma string com todos os dados da lista da fila.
     */
    @Override
    public String toString ()
    {
        return this.lista.toString();
    }

    /**
     * Equals, compara a fila a outra classe para verificar se são iguais.
     * 
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura
     *
     * @return retorna true se as classes forem iguais e false se forem diferentes.
     */
    @Override
    public boolean equals (Object obj)
    {
        return this.lista.equals(obj);
    }

    /**
     * HashCode, retorna o HashCode da classe.
     * 
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura
     *
     * @return retorna um int, o hashcode da classe.
     */
    @Override
    public int hashCode ()
    {
        return this.lista.hashCode();
    }

    /**
     * Construtor de cópia, instância uma classe igual ao modelo fornecido.
     * 
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura
     *
     * @param modelo modelo utilizado para instanciar uma nova classe.
     * @throws Exception se o modelo for nulo.
     */
    public Fila (Fila<X> modelo) throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo ausente");
        
        this.lista = modelo.lista;
    }

    /**
     * Clone, clona a classe.
     * 
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura
     *
     */
    @Override
    public Object clone ()
    {
        Fila<X> ret=null;

        try
        {
            ret = new Fila<X> (this);
        }
        catch (Exception erro)
        {}

        return ret;
    }
}
