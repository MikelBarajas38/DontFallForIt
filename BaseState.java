
public interface BaseState  
{
    
    void enter();
    State input();
    State process();
    void exit();
    
}
