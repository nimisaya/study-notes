# Application Class

Application class is a base class of Android app containing components like Activities and Services. It (and its sub classes) are instantiated before other application objects (e.g. activities) have been created.

They are predefined so you don't have to import or extend them but you can extend them if you need to e.g. using Hilt for dependency injection.
