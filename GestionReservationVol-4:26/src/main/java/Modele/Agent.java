/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author echo
 */
// agent a besoin d'heriter les attributs de classe Personne dans le constructor
public class Agent extends Personne {
      private String loginAgent;
    private String mdpAgent;
    private String compagnieAgent;

    public Agent(String l, String m, String c) {
        super();
        loginAgent = l;
        mdpAgent = m;
        compagnieAgent = c;
    }

    public String getLoginAgent() {
        return loginAgent;
    }

    public void setLoginAgent(String loginAgent) {
        this.loginAgent = loginAgent;
    }

    public String getMdpAgent() {
        return mdpAgent;
    }

    public void setMdpAgent(String mdpAgent) {
        this.mdpAgent = mdpAgent;
    }

    public String getCompagnieAgent() {
        return compagnieAgent;
    }

    public void setCompagnieAgent(String compagnieAgent) {
        this.compagnieAgent = compagnieAgent;
    }

    public void modifierMdp(String mdp) {

        setMdpAgent(mdp);
    }
    
    
    
}
