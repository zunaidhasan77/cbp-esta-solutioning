# Java version
Its needed Java 21

# Execute below to build it:

mvn clean install

# Run the test:

mvn clean verify -Dwebdriver.base.url=https://esta.cbp.dhs.gov/ -Dcucumber.filter.tags="@smoke" -Dwebdriver.driver=chrome serenity:aggregate

1. 🔍 Validate in browser manually if 508 axe can be injected:

- Go to https://esta.cbp.dhs.gov, open DevTools → Console and run:
  var s = document.createElement('script');
  s.src = 'https://cdnjs.cloudflare.com/ajax/libs/axe-core/4.8.2/axe.min.js';
  document.head.appendChild(s);
- If it fails, We will get CSP error.
- Refused to load the script '...' because it violates the following Content Security Policy directive...
