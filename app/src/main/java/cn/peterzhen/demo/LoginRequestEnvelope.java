package cn.peterzhen.demo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

/**
 * 当前类注释:
 *
 * @author zhenyanjun
 * @date 2018/12/22 19:33
 */
@Root(name = "soapenv:Envelope")
@NamespaceList({
        @Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/",prefix = "soapenv"),
        @Namespace(reference = "http://dao.ws.cbsw.cn/",prefix = "dao")
})
public class LoginRequestEnvelope {

    @Element(name = "soapenv:Header",required = false)
    String header;

    @Element(name = "soapenv:Body")
    LoginRequestBody body;

    @Root(name = "soapenv:Body")
    class LoginRequestBody{
        @Element(name = "dao:login")
        LoginRequestParams params;

        @Root(name = "dao:login")
        class LoginRequestParams{
            @Element(name = "username")
            String username;

            @Element(name = "password")
            String password;

            @Element(name = "shebei")
            String shebei;

        }
    }

}
