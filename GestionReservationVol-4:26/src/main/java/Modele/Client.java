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
public class Client extends Personne{
    private String login;
    private String mdpClient;
   
   
    public Client(){

    }

    public Client(String n,String p,String np,String a,String l, String m){
        super(n,p,np,a);
        login=l;
        mdpClient=m;
    }

    //get(),set()

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdpClient() {
        return mdpClient;
    }

    public void setMdpClient(String mdpClient) {
        this.mdpClient = mdpClient;
    }

    public void modifierMdp(String mdp){
        setMdpClient(mdp);
        }

}
