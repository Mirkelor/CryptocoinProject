# CryptocoinProject
How to Deploy Spring Boot + MySQL App to Azure (FREE TIER)

Creating Azure Database for MySQL Servers
1. Enter Azure Database for MySQL to search bar. Create new database.
2. Write your desired server name.
3. Select your subscription.
4. Select your Resource Group.  If you dont have existing Resource Group you need to create.
5. Select source. We used "Blank".
6. Create your admin login name, password.
7. Select your location and version.
8. Pricing tier: We used standart General Purpose tier.
9. Go to your database settings --> Connection Security.
10. Add client Ip then save it.
This will allow your MySQL Workbench to connect Azure Database after you.
11. Go to Connection Strings.
12. Copy your server url to your app (we used JDBC).

Go to MySQL Workbench
1. Host name (serverName).mysql.database.azure.com
2. Create new server with username and password which you created.
(which Azure Database for MySQL Server --> Overview page has hostname and your username will be Server Admin login name.)
3. Port: enter your app port (we used 3306)
4. Test Connection if you get successful go to next step.

Deploying App with Azure Toolkit for IntelliJ Plugin
1. If you dont have this plugin please install to your intellij.
2. Right click to your project -> Azure -> Deploy to Azure.
3. Name: create your configuration name.
4. Artifact: select your project's artifact.
5. WebApp select "Create New WebApp".
6. Name: select your website name.
7. Subscription: select your subscription.
8. Platform: Windows.
9. Web Container: Tomcat 8.5.
10. Resource Group: select "Create New" -> enter your new Resource Group Name.
11. App Service Plan: select "Create New".
12. ServicePlan: create your service plan name.
13. Location: select your location.
14. Pricing Tier: Select your tier (We used Free_F1).
15. Click OK.
16. Click Run.
