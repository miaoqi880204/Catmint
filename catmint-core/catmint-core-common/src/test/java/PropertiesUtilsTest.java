import org.catmint.core.tools.common.PropertiesUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class PropertiesUtilsTest {
    @Test
    public void readValue() {
        Assert.assertNotNull( PropertiesUtils.readValue( "config.properties","org.catmint.zk.address" ) );
    }
}
