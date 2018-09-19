<?xml version="1.0" encoding="UTF-8"?>
<TestCase name="Task3unique_db" version="5">

<meta>
   <create author="admin" buildNumber="10.0.0.431" date="06/20/2017" host="inbasdpc10722" version="10.0.0"/>
   <lastEdited author="admin" buildNumber="10.0.0.431" date="06/20/2017" host="inbasdpc10722" version="10.0.0"/>
</meta>

<id>5689E886558111E7BB81D8CB8A8AB1DA</id>
<Documentation>Put documentation of the Test Case here.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9LTEmbGlzYXY9MTAuMC4wICgxMC4wLjAuNDMxKSZub2Rlcz0xMjU3MDcwOTg1</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

    <Node log="" name="JDBC_unique" next="end" quiet="false" think="500-1S" type="com.itko.lisa.jdbc.JDBCNode" uid="5A8FDFFC558111E7BB81D8CB8A8AB1DA" useFilters="true" version="1"> 

<driver>org.apache.derby.jdbc.ClientDriver</driver>
<dataSourceConnect>false</dataSourceConnect>
<jndiFactory/>
<jndiServerURL/>
<jndiDataSourceName/>
<connect>jdbc:derby://localhost:1528/database/lisa.db</connect>
<user>sa</user>
<password_enc>l91d84efecea722e4d0e158b165c41e822bedfdd0a68564de91b842c4290b782a9262</password_enc>
<onSQLError>abort</onSQLError>
<resultSet>true</resultSet>
<maxRows>-1</maxRows>
<keepOpen>false</keepOpen>
<usePool>true</usePool>
<sql>select * from emp_utab</sql>
<IsStoredProc>false</IsStoredProc>
    </Node>


    <Node log="" name="end" next="fail" quiet="true" think="0h" type="com.itko.lisa.test.NormalEnd" uid="5689E88C558111E7BB81D8CB8A8AB1DA" useFilters="true" version="1"> 

    </Node>


    <Node log="" name="fail" next="abort" quiet="true" think="0h" type="com.itko.lisa.test.Abend" uid="5689E88A558111E7BB81D8CB8A8AB1DA" useFilters="true" version="1"> 

    </Node>


    <Node log="" name="abort" next="" quiet="true" think="0h" type="com.itko.lisa.test.AbortStep" uid="5689E888558111E7BB81D8CB8A8AB1DA" useFilters="true" version="1"> 

    </Node>


</TestCase>