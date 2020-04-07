console.log("loading createFramedApp");
import Postmate from 'postmate';


class Portal {
    
    constructor() {
      console.log("new portal");
      this.children = new Map();
      this.activeCtx = '';
    
    
    this.handleNavigation = (event) => { 
      console.log('Navigation Event: ' + JSON.stringify(event) );
      var ctx = event.activeApp;
      var path = event.setUrl;
      
      if(ctx !== this.activeCtx){
        console.log('context change: ' + this.activeCtx + ' -> ' + ctx);
        var activeChild = this.children.get(ctx);
        var inactiveChild = this.children.get(this.activeCtx);
        this.activeCtx = ctx;
        //TODO workarount: need observer
        var tab = document.getElementById('tab-' + ctx);
        var tabs = document.getElementById('tabs');
        tabs.appid = event.activeApp;
        if(tab){
          tab.click();
        }
        var child = this.children.get(ctx);
        if(child){
           child.get('location').then( location => {
              path = path || JSON.parse(location).pathname;
              history.pushState({}, '', path);
            })
        };
        
        console.log(this.activeCtx);
      }else{
        history.pushState({}, '', ctx + '/' + path);
      }
    }

    this.switchContext = (windowName) => {
      console.log(window[windowName]);
      var event = {
        activeApp: windowName
      };
      this.handleNavigation(event);
    }

    this.createFramedApp = (containerId, url, name) => {
      var _this = this;
      var name = name;
        console.log("Setting up: " + name);


    
        const handshake =  new Postmate({
            container: document.getElementById(containerId), // Element to inject frame into
            url: url, // Page to load, must have postmate.js. This will also be the origin used for communication.
            name: name, // Set Iframe name attribute. Useful to get `window.name` in the child.
            classListArray: ["appFrame"] //Classes to add to the iframe via classList, useful for styling.
        });
    
        return handshake.then(child => {
          console.log('Handshake Child (' + name + ') complete');
          this.children.set(name, child);
          child.on('navigation', _this.handleNavigation);
          return child;
        });
      }

}}
window.portal = new Portal();
