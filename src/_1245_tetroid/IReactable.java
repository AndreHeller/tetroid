package _1245_tetroid;

/**
 * Třídy implementující IRactable jsou schopné reagovat na události
 * definované v ReactEvent.
 *
 * @author Tetroids
 */
public interface IReactable
{
    /***************************************************************************
     * Vyvolá reakci na různé typy ReactEventů u tříd implemetujících
     * toto rozhraní.
     *
     * @param reactevent daná událost, na kteoru je třeba reagovat
     *
     */
    public abstract void react(ReactEvents reactevent);
}
