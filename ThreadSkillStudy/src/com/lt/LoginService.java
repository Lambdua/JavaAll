package com.lt;

/**
 * @author liangtao
 * @Date 2019/12/4
 **/
public class LoginService {
    private static String usernameRef;
    private static String passwdRef;

    public static void doPost(String username, String passwd) {
        usernameRef = username;
        if (usernameRef.equals("a")) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        passwdRef = passwd;
        System.out.println("username:" + usernameRef + " passwd:" + passwdRef);
    }


    public static void main(String[] args) {
        Thread aLogin = new Thread(() -> {
            LoginService.doPost("a", "aaa");
        });
        Thread bLogin = new Thread(() -> {
            LoginService.doPost("b", "bbb");
        });

        aLogin.start();
        bLogin.start();
    }
}
