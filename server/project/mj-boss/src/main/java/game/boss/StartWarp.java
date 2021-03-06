package game.boss;

import com.isnowfox.util.ArrayExpandUtils;
import game.boss.admin.AdminListenServer;
import game.boss.net.GatewayListenServer;
import game.boss.net.SceneListenServer;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

public class StartWarp {
    public static void start(String[] args) {
        IocFactoryProxy ioc = null;
        DOMConfigurator.configure(StartWarp.class.getResource("/bossLog4j.xml"));
          org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BossMain.class);
        logger.error("qqq");
        logger.debug("slf4j debug  test----");
        Logger log =    Logger.getLogger(BossMain.class);
        log.debug("log4j debug  test----");
        log.info(" log4j  info test----");
        log.error("qqq11111");

        try {
            ioc = IocFactoryProxy.getInstance();
            ioc.init();
            GatewayListenServer gatewayListenServer = ioc.getBean("gatewayListenServer", GatewayListenServer.class);
            gatewayListenServer.start();

            SceneListenServer sceneListenServer = ioc.getBean("sceneListenServer", SceneListenServer.class);
            sceneListenServer.start();


            AdminListenServer adminListenServer = ioc.getBean("adminListenServer", AdminListenServer.class);
            adminListenServer.start();
            System.gc();
            log.info("服务器启动完毕！");
        } catch (Throwable e) {
            log.error("服务器启动错误,准备关闭", e);
            if (ioc != null) {
                ioc.destroy();
            }
        }


    }
}
