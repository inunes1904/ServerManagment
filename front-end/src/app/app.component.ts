import {Component, OnInit} from '@angular/core';
import {ServerService} from "./service/server.service";
import {map, Observable} from "rxjs";
import {AppState} from "./interface/app-state";
import {CustomResponse} from "./interface/custom-response";
import {DataState} from "./enum/data-state.enum";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  appState$: Observable<AppState<CustomResponse>>;

  constructor(private serverService: ServerService) {
  }

  ngOnInit(){
    this.appState$ = this.serverService.servers$
      .pipe(map(response => {
        return {dataState: DataState.LOADED_STATE, appData : response}
      }))
  }

}
