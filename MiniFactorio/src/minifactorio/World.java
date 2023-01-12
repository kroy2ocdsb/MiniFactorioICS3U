/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minifactorio;

/**
 *
 * @author keega
 */
public class World {
    private Environment curEnvironment;
    private Environment[] environments;
    
    public World(Environment[] _environments, int _curEnvironmentIndex) {
        environments = _environments;
        setCurEnvironment(_curEnvironmentIndex);
    }
    
    public Environment getCurEnvironment() {
        return curEnvironment;
    }
    
    public void setCurEnvironment(int curEnvironmentIndex) {
        // TODO: add on change method calls for Environment
        
        curEnvironment = environments[curEnvironmentIndex];
    }
}
