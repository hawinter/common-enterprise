import {Component} from '@angular/core';
import {ToasterContainerComponent, ToasterService, ToasterConfig} from 'angular2-toaster';
 
@Component({
    selector: 'notification-container',
    providers: [ToasterService],
    template: `
        <toaster-container [toasterconfig]="toasterconfig">
        </toaster-container>
    `
})
 
export class NotificationComponent { 
    public toasterconfig : ToasterConfig = 
        new ToasterConfig({
            showCloseButton: true, 
            tapToDismiss: false, 
            timeout: 3000
        });
}