# CryptocoinProject
<h2>How to Deploy Spring Boot + MySQL App to Azure (FREE TIER)</h2>

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

<h2>How to Deploy Spring Boot App to Azure Web App with Docker (Free Tier)</h2>

Creating new Container Registry
1. Go to search bar write "container registries"
2. Add: create new container registry.
3. Registry name: select your container name.
4. Subscription: select your subscription.
5. Resource Group: select or create your resource group.
6. Location: select location.
7. Admin user: select enable.
8. SKU: select "standard".
9. Create.

Building and Pushing Docker Image
1. For creating dockerfile we used "Azure Toolkit for IntelliJ" plugin.
2. Go to intellij -> right click to your project.
3. Azure -> Add Docker Support (it will create your dockerfile).
4. Go to azure portal -> Container registry which you created -> Access keys.
5. You will see your "Registry name", "Login server", "Username", "password", "password2".
6. Go to your project file inside then open terminal there -> docker login "Your Login Server Name".
7. Enter your username and password from azure portal access keys.
8. Write -> docker build -t "Your Login Server Name"/"App name in container which you just will create":"Tag name(we used latest)".
9. Some example -> docker build -t demoapp.azurecr.io/demo-app:latest . (after one space used dot).
10. Now push your project with docker -> docker push "Your Login Server Name"/"App name which you created":"Tag name(we used latest)".
11. Some example -> docker push demoapp.azurecr.io/demo-app:latest

Creating Azure Web App
1.  Go to search bar write "web app for containers".
2. App name: write your website name.
3. Subscription: select your subscription.
4. Resource Group: Select your resource group.
5. OS: Select Linux
6. App Service Plan: Click -> Create New -> 
7. App Service Plan: write your app service plan
8. Location: select your location
9. Pricing tier: select your tier
10. Select OK
11. Configure container: click ->
12. Image source: select "Azure Container Registry"
13. Registry: select your registry
14. Image: select your image
15. Tag: select your tag
16. Apply.
17. Create.

