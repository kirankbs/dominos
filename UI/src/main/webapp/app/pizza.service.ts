import { Headers, Http, Response }          from '@angular/http'
import {Injectable}                         from '@angular/core';

import 'rxjs/add/operator/toPromise';

import { Pizza }                            from './Pizza';
import { Toppings }                          from './Toppings'
import { Crust }                             from './Crust'   
import { ToppingsList }                       from './mock-pizzas';
import { CrustsList }                       from './mock-pizzas';

@Injectable()
export class PizzaService{

    //private _pizzassUrl = 'http://localhost:9082/pizzas';
    private _pizzassUrl = 'app/pizzas';
    private _toppingsUrl = 'app/toppings';

    constructor(private http: Http){}

    getPizzas(): Promise<Pizza[]> {
        let headers = new Headers([{
      'Content-Type': 'application/json'},
      {'Access-Control-Allow-Origin':'*'}
        ]);

        //return Promise.resolve(PizzasList);
        return this.http.get(this._pizzassUrl, {headers: headers})
                    .toPromise()
                    .then(response => response.json().data as Pizza[])
                    .catch(this.handleError);
    }
    getToppings(): Promise<Toppings[]> {
              return new Promise(resolve => resolve(ToppingsList));
    }

    getCrusts(): Promise<Crust[]>{
            return new Promise(resolve => resolve(CrustsList))
    }

    getPizza(name: String): Promise<Pizza> {
        return this.getPizzas().then(pizzas => pizzas.find(pizza => pizza.pizzaName === name))
    }

    save(pizza: Pizza): Promise<Pizza>  {
    if (pizza.pizzaName) {
      return this.put(pizza);
    }
    return this.post(pizza);
  }

  delete(pizza: Pizza): Promise<Response> {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    let url = `${this._pizzassUrl}/${pizza.pizzaName}`;
    return this.http
               .delete(url, {headers: headers})
               .toPromise()
               .catch(this.handleError);
  }

  private post(pizza: Pizza): Promise<Pizza> {
    let headers = new Headers({
      'Content-Type': 'application/json'});
    return this.http
               .post(this._pizzassUrl, JSON.stringify(pizza), {headers: headers})
               .toPromise()
               .then(res => res.json().data)
               .catch(this.handleError);
  }

  private put(pizza: Pizza): Promise<Pizza> {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    let url = `${this._pizzassUrl}/${pizza.pizzaName}`;
    console.info(url);
    console.info(JSON.stringify(pizza));
    return this.http
               .put(url, JSON.stringify(pizza), {headers: headers})
               .toPromise()
               .then(() => pizza)
               .catch(this.handleError);
  }

    private handleError(error: any): Promise<any> {
         console.error('An error occurred', error);
         return Promise.reject(error.message || error);
    }
}