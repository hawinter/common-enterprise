import { Injectable } from '@angular/core';
//import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToasterContainerComponent, ToasterService, ToasterConfig } from 'angular2-toaster';


@Injectable()
export class NotificationService {
    private toasterService: ToasterService;

    constructor(toasterService: ToasterService) {
        this.toasterService = toasterService;
    }

    public toasterconfig: ToasterConfig =
        new ToasterConfig({
            showCloseButton: true,
            tapToDismiss: false,
            timeout: 3000
        });

    success(title: string, body: string) {
        this.toasterService.pop('success', title, body);
    }

    info(title: string, body: string) {
        this.toasterService.pop('info', title, body);
    }

    warning(title: string, body: string) {
        this.toasterService.pop('warning', title, body);
    }

    error(title: string, body: string) {
        this.toasterService.pop('error', title, body);
    }
}
