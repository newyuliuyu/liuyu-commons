package com.liuyu.common.db;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * ClassName: InitDataSource <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-11 下午3:25 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
public class InitDataSource {
//    private static Logger logger = LoggerFactory.getLogger(InitDataSource.class);

    public static DataSource init(DatasourceProperties datasourceProperties) {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(datasourceProperties.getUrl());
        datasource.setUsername(datasourceProperties.getUsername());
        datasource.setPassword(datasourceProperties.getPassword());
        datasource.setDriverClassName(datasourceProperties.getDriverClassName());

        //configuration
        datasource.setInitialSize(datasourceProperties.getInitialSize());
        datasource.setMinIdle(datasourceProperties.getMinIdle());
        datasource.setMaxActive(datasourceProperties.getMaxActive());
        datasource.setMaxWait(datasourceProperties.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(datasourceProperties.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(datasourceProperties.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(datasourceProperties.getValidationQuery());
        datasource.setTestWhileIdle(datasourceProperties.isTestWhileIdle());
        datasource.setTestOnBorrow(datasourceProperties.isTestOnBorrow());
        datasource.setTestOnReturn(datasourceProperties.isTestOnReturn());
        datasource.setPoolPreparedStatements(datasourceProperties.isPoolPreparedStatements());
        datasource.setMaxPoolPreparedStatementPerConnectionSize(datasourceProperties.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            datasource.setFilters(datasourceProperties.getFilters());
        } catch (SQLException e) {
            log.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(datasourceProperties.getConnectionProperties());

        return datasource;
    }
}
