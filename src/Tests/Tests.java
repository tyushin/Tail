package Tests;

import Tail.Tail;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;


public class Tests {

    private final String text1 = "";
    private final String text2 = "Password authentication doesn’t scale well.\n" +
            "The more services we use, the more passwords we’re forced to remember.\n" +
            "In the name of security, SaaS applications,\n" +
            "social networks and other services enforce strict password rules that prevent honest people from signing in.\n" +
            "Username/password authentication is apparently so effective that it’s a serious barrier to product and service use.\n" +
            "It’s not the passwords themselves;\n" +
            "the problem is the scale at which people have to manage and remember usernames and passwords. It’s too much.\n" +
            "We don’t want to make our products less secure by relaxing our password standards,\n" +
            "so what are our real options to safely and securely authenticate people and protect their sensitive information?\n" +
            "There are a handful of ways today, and there are more coming.\n" +
            "There are even things we can do to make traditional password authentication frictionless and user-friendly.\n" +
            "Here are the options we realistically have today.";

    private final String result1 = "In the name of security, SaaS applications,\n" +
            "social networks and other services enforce strict password rules that prevent honest people from signing in.\n" +
            "Username/password authentication is apparently so effective that it’s a serious barrier to product and service use.\n" +
            "It’s not the passwords themselves;\n" +
            "the problem is the scale at which people have to manage and remember usernames and passwords. It’s too much.\n" +
            "We don’t want to make our products less secure by relaxing our password standards,\n" +
            "so what are our real options to safely and securely authenticate people and protect their sensitive information?\n" +
            "There are a handful of ways today, and there are more coming.\n" +
            "There are even things we can do to make traditional password authentication frictionless and user-friendly.\n" +
            "Here are the options we realistically have today.";
    private final String result2 = "There are a handful of ways today, and there are more coming.\n" +
            "There are even things we can do to make traditional password authentication frictionless and user-friendly.\n" +
            "Here are the options we realistically have today.";
    private final String result3 = "so what are our real options to safely and securely authenticate people and protect their sensitive information?\n" +
            "There are a handful of ways today, and there are more coming.\n" +
            "There are even things we can do to make traditional password authentication frictionless and user-friendly.\n" +
            "Here are the options we realistically have today.";
    private final String result4 = "Password authentication doesn’t scale well.\n" +
            "The more services we use, the more passwords we’re forced to remember.\n" +
            "In the name of security, SaaS applications,\n" +
            "social networks and other services enforce strict password rules that prevent honest people from signing in.\n" +
            "Username/password authentication is apparently so effective that it’s a serious barrier to product and service use.\n" +
            "It’s not the passwords themselves;\n" +
            "the problem is the scale at which people have to manage and remember usernames and passwords. It’s too much.\n" +
            "We don’t want to make our products less secure by relaxing our password standards,\n" +
            "so what are our real options to safely and securely authenticate people and protect their sensitive information?\n" +
            "There are a handful of ways today, and there are more coming.\n" +
            "There are even things we can do to make traditional password authentication frictionless and user-friendly.\n" +
            "Here are the options we realistically have today.";

    private final Tail tail1 = new Tail(null, null);
    private final Tail tail2 = new Tail(15, true);
    private final Tail tail3 = new Tail(219, true);
    private final Tail tail4 = new Tail(10, false);
    private final Tail tail5 = new Tail(4, false);
    private final Tail tail6 = new Tail(100, false);


    @Test
    public void pickTail() throws Exception {
        Charset charset = StandardCharsets.UTF_8;
        assertEquals("", tail2.pickTail(new ByteArrayInputStream(text1.getBytes(charset))));
        assertEquals("lly have today.", tail2.pickTail(new ByteArrayInputStream(text2.getBytes(charset))));
        assertEquals(result1, tail1.pickTail(new ByteArrayInputStream(text2.getBytes(charset))));
        assertEquals(result1, tail4.pickTail(new ByteArrayInputStream(text2.getBytes(charset))));
        assertEquals(result2, tail3.pickTail(new ByteArrayInputStream(text2.getBytes(charset))));
        assertEquals(result3, tail5.pickTail(new ByteArrayInputStream(text2.getBytes(charset))));
        assertEquals(result4, tail6.pickTail(new ByteArrayInputStream(text2.getBytes(charset))));
    }

    @Test
    public void pickFileTail() throws Exception {
        assertEquals(result1, tail4.pickFileTail("files\\text2.txt"));
        //assertEquals("", tail1.pickFileTail("files\\text1.txt"));
        assertEquals(result2, tail3.pickFileTail("files\\text2.txt"));
        assertEquals(result3, tail5.pickFileTail("files\\text2.txt"));
        assertEquals("lly have today.", tail2.pickFileTail("files\\text2.txt"));
        assertEquals(result4, tail6.pickFileTail("files\\text2.txt"));
    }
}
