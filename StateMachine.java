import java.util.EnumMap;

public interface StateMachine  
{
    
    public EnumMap<State, BaseState> getStates();
    public void handleState();
    
}
