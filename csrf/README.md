Secure Form CSRF Snippet
---------------

This view snippet is for including csrf in a cleaner style to the view than ```@form(CSRF(action))```

You can use this like the normal helper, just write secureform instead of form, it includes the token in a hidden input not in the url like the built in version:
```
@secureform(action)

```

You have to use the CSRFFilter, extend your Global class like this:

```
object Global extends WithFilters(CSRFFilter()) with GlobalSettings {

```

This only works in 2.1 because the filters became available in this version.