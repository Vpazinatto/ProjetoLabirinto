package projetolabirinto;

import java.lang.reflect.*;

/**
 * Esta classe consiste em métodos para armazenar dados em nós, na forma de lista desordenada.
 *
* @author Vinicius Pazinatto
* @author Daniel Carvalho de Moura
* 
 * @param <X> tipo dos dados que devem ser armazenados
 */
public class ListaDesordenada<X> {
    
    /**
    * Esta classe consiste em um nó que armazena alguma informação, e o próximo nó na lista.
    *
    * @author Vinicius Pazinatto
    * @author Daniel Carvalho de Moura
    * 
    */
    private class No
    {
        private X  info;
        private No prox;

        /**
        * Construtor, define a informação e o nó seguinte do nó atual.
        *
        * @author Vinicius Pazinatto
        * @author Daniel Carvalho de Moura
        * 
        */
        public No (X i, No p)
        {
            this.info = i;
            this.prox = p;
        }

        /**
        * GetInfo, getter responsável por retornar a informação do nó
        *
        * @author Vinicius Pazinatto
        * @author Daniel Carvalho de Moura
        * 
        * @return retorna a informação armazenada neste nó
        */
        public X getInfo ()
        {
            return this.info;
        }

        /**
        * GetProx, getter responsável por retornar o nó seguinte
        *
        * @author Vinicius Pazinatto
        * @author Daniel Carvalho de Moura
        * 
        * @return retorna o nó seguinte
        */
        public No getProx ()
        {
            return this.prox;
        }

        /**
        * SetInfo, Setter responsável por definir a informação do nó
        *
        * @author Vinicius Pazinatto
        * @author Daniel Carvalho de Moura
        * 
        * @param i informação que será armazenada
        */
        public void setInfo (X i)
        {
            this.info = i;
        }

        /**
        * SetProx, setter responsável por definir qual o próximo nó da lista.
        *
        * @author Vinicius Pazinatto
        * @author Daniel Carvalho de Moura
        * 
        * @param p nó que será apontado como próximo
        */
        public void setProx (No p)
        {
            this.prox = p;
        }
    }
    
    private No prim;
    private No ulti;
    
    /**
    * Construtor, instancia a lista definindo o primeiro e ultimo nó como nulos.
    *
    * @author Vinicius Pazinatto
    * @author Daniel Carvalho de Moura
    * 
    */
    public ListaDesordenada() 
    {
        this.prim = null;
        this.ulti = null;
    }
    
    /**
     * MeuCloneDeX, Método para clonar dados para armazenar ou retornar.
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
     * Vazia, método que informa se a lista está vazia.
     * 
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura
     *
     * @return retorna true se a lista estiver vazia, e false se não estiver vazia.
     */
    public boolean vazia() 
    {
        if (this.prim == null)
            return true;
        else
            return false;
    }
    
    
    /**
     * InsiraNoTopo, método para inserir dados na lista, para ser utilizado pela classe Pilha.
     *
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura
     * 
     * @param i informação que deve ser inserida
     * @throws Exception se o parâmetro for nulo
     */
    public void insiraNoTopo(X i) throws Exception 
    {
        if (i==null)
           throw new Exception ("Informação ausente !");
        
        if (this.prim == null) 
        {
            this.prim = new No(i, null);
            this.ulti = this.prim;
            return;
        }
        
        No novo = new No(i, this.ulti);
        this.ulti = novo;
    }
    
    /**
     * InsiraNoFinal, método para inserir dados na lista, para ser utilizado pela classe Fila.
     *
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura
     * 
     * @param i informação que deve ser inserida
     * @throws Exception se o parâmetro for nulo
     */
    public void insiraNoFinal(X i) throws Exception 
    {
        if (this.prim == null) {
            this.prim = new No(i, null);
            this.ulti = this.prim;
            return;
        }
        
        if (this.prim.getProx() == null)
        {
            this.ulti = new No(i, null);
            this.prim.setProx(this.ulti);            
            return;
        }
      
        this.ulti.setProx(new No (i, null));
        this.ulti = this.ulti.getProx();
    }
    
    /**
     * RemovePrimeiro, método para remover o primeiro nó da lista, para ser utilizado pela classe Fila.
     *
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura
     * 
     * @throws Exception se a lista estiver vazia.
     */
    public void removePrimeiro()  throws Exception
    {
        if (this.prim == null)    
            throw new Exception ("Lista vazia !");
        
        this.prim = this.prim.getProx();

    }
    
    /**
     * RemoveUltimo, método para remover o ultimo nó da lista, para ser utilizado pela classe Pilha.
     *
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura
     * 
     * @throws Exception se a lista estiver vazia.
     */
    public void removeUltimo() throws Exception
    {
        if (this.ulti == null)
            throw new Exception ("Lista vazia !");
        
        this.ulti = this.ulti.getProx();
    }
    
    /**
     * GetPrimeiro, getter responsável por retornar o primeiro nó da lista.
     *
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura
     * 
     */
    public X getPrimeiro()
    {
        return this.prim.info;
    }
    
    /**
     * GetPrimeiro, getter responsável por retornar o ultimo nó da lista.
     *
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura
     * 
     */
    public X getUltimo()
    {
        return this.ulti.info;
    }
    
    /**
     * Equals, compara a lista a outra classe para verificar se são iguais.
     * 
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura
     *
     * @return retorna true se as classes forem iguais e false se forem diferentes.
     */
    @Override
    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (this.getClass()!=obj.getClass())
            return false;

        ListaDesordenada<X> lis=(ListaDesordenada<X>)obj;

        No atualThis=this.prim, atualLis=lis.prim;

        while (atualThis!=null && atualLis!=null)
        {
                if (!atualThis.getInfo().equals(atualLis.getInfo()))
                        return false;

                atualThis=atualThis.getProx();
                atualLis =atualLis .getProx();
        }

        if (atualThis!=null)
                return false;

        return atualLis == null;
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
        int ret=666;

        No atual=this.prim;
        while (atual!=null)
        {
            ret = ret*7 + atual.getInfo().hashCode();
            atual= atual.getProx();
        }

        return ret;
    }
    
    /**
     * ToString, retorna uma lista de nós com suas respectivas informações armazenadas.
     * 
     * @author Vinicius Pazinatto
     * @author Daniel Carvalho de Moura
     *
     * @return retorna uma string com todos os dados dos nós da lista.
     */
    @Override
    public String toString ()
    {
        String ret="";

        No atual=this.prim;
        while (atual!=null)
        {
            ret += atual.getInfo()+" ";
            atual= atual.getProx();
        }

        return ret;
    }
}
