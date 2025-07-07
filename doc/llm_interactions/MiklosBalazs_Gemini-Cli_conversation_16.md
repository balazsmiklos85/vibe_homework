# 2025-07-07

## 20:36

> When I build my project, I get the warning `Detected bean archive with bean discovery mode of 'all', this is not portable in CDI Lite and is treated as 'annotated' in Quarkus! Path to beans.xml: hello-service:application:unspecified:/META-INF/beans.xml`. How can this be made portable in CDI Lite?

✦ The warning indicates that the bean-discovery-mode in your beans.xml file is set to all, which is not portable in CDI Lite. To fix this, you should change it to annotated.

First, I'll locate and read the beans.xml file to confirm its contents. Based on the project structure, it should be at application/src/main/resources/META-INF/beans.xml.

**ReadFile** `...tion/main/resources/META-INF/beans.xml`

✦ The file confirms the bean-discovery-mode is set to all. I will now change it to annotated to resolve the warning and ensure CDI Lite portability.

**Edit** `...tion/main/resources/META-INF/beans.xml:        bean-discovery-mode="al... =>        bean-discovery-mode="an...`

✦ I have updated application/src/main/resources/META-INF/beans.xml by setting the bean-discovery-mode to annotated. This change will make your bean archive configuration portable in CDI Lite and should resolve the warning you were seeing.


