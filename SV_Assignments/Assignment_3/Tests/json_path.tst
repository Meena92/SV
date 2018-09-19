<?xml version="1.0" encoding="UTF-8"?>
<TestCase name="json_path" version="5">

<meta>
   <create author="admin" buildNumber="10.0.0.431" date="06/19/2017" host="inbasdpc10722" version="10.0.0"/>
   <lastEdited author="admin" buildNumber="10.0.0.431" date="06/20/2017" host="inbasdpc10722" version="10.0.0"/>
</meta>

<id>40295FF754D511E78802D8CB8A8AB1DA</id>
<Documentation>Put documentation of the Test Case here.</Documentation>
<IsInProject>true</IsInProject>
<sig>ZWQ9NSZ0Y3Y9NSZsaXNhdj0xMC4wLjAgKDEwLjAuMC40MzEpJm5vZGVzPS0yMTI2MDQxMDU5</sig>
<subprocess>false</subprocess>

<initState>
</initState>

<resultState>
</resultState>

<deletedProps>
</deletedProps>

    <Node log="" name="Parse Text as Response" next="end" quiet="true" think="500-1S" type="com.itko.lisa.utils.ParseTextContentNode" uid="5967791054D511E78802D8CB8A8AB1DA" useFilters="true" version="1"> 


      <!-- Filters -->
      <Filter type="com.ca.lisa.apptest.json.FilterJSONGet">
        <valueToFilterKey>lisa.Parse Text as Response.rsp</valueToFilterKey>
      <jsonPath>$.user.emailAddress</jsonPath>
      <valueProp>Email</valueProp>
      <lengthProp/>
      </Filter>

      <Filter type="com.ca.lisa.apptest.json.FilterJSONGet">
        <valueToFilterKey>lisa.Parse Text as Response.rsp</valueToFilterKey>
      <jsonPath>$.user.firstName</jsonPath>
      <valueProp>FName</valueProp>
      <lengthProp/>
      </Filter>

      <Filter type="com.ca.lisa.apptest.json.FilterJSONGet">
        <valueToFilterKey>lisa.Parse Text as Response.rsp</valueToFilterKey>
      <jsonPath>$.user.lastName</jsonPath>
      <valueProp>LName</valueProp>
      <lengthProp/>
      </Filter>

      <Filter type="com.ca.lisa.apptest.json.FilterJSONGet">
        <valueToFilterKey>lisa.Parse Text as Response.rsp</valueToFilterKey>
      <jsonPath>$.user.password</jsonPath>
      <valueProp>PWD</valueProp>
      <lengthProp/>
      </Filter>

      <Filter type="com.ca.lisa.apptest.json.FilterJSONGet">
        <valueToFilterKey>lisa.Parse Text as Response.rsp</valueToFilterKey>
      <jsonPath>$.user.username</jsonPath>
      <valueProp>UName</valueProp>
      <lengthProp/>
      </Filter>

<text>ew0KInVzZXIiOnsNCiJlbWFpbEFkZHJlc3MiOiJhYmNAdGVzdC5jb20iLA0KImZpcnN0TmFtZSI6IkFiYyIsDQoibGFzdE5hbWUiOiJjZGUiLA0KInBhc3N3b3JkIjoiYmJiYiIsDQoidXNlcm5hbWUiOiJhYmNfY2RlIg0KfQ0KfQ==</text>
<propKey/>
    </Node>


    <Node log="" name="abort" next="" quiet="true" think="0h" type="com.itko.lisa.test.AbortStep" uid="40295FF954D511E78802D8CB8A8AB1DA" useFilters="true" version="1"> 

    </Node>


    <Node log="" name="fail" next="abort" quiet="true" think="0h" type="com.itko.lisa.test.Abend" uid="40295FFB54D511E78802D8CB8A8AB1DA" useFilters="true" version="1"> 

    </Node>


    <Node log="" name="end" next="fail" quiet="true" think="0h" type="com.itko.lisa.test.NormalEnd" uid="40295FFD54D511E78802D8CB8A8AB1DA" useFilters="true" version="1"> 

    </Node>


</TestCase>