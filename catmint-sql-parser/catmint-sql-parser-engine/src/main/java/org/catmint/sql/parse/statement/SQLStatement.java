package org.catmint.sql.parse.statement;

/**
* <p>Title:SQLStatement parent interface</p>
* <p>Description:</p>
* @author QIQI
* @params
* @return 
* @throws 
* @date 2021/3/6 上午10:38 
*/
public interface SQLStatement{
    
    /**
     * Get count of parameters.
     *
     * @return count of parameters
     */
    int getParameterCount();
}
