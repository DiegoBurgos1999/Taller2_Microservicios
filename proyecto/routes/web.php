<?php

use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Route::get('/home', '\App\Http\Controllers\PagesController@inicio');
Route::get('/paseos','\App\Http\Controllers\PagesController@index');
Route::get('/paseos/eliminacion/{id}','\App\Http\Controllers\PagesController@eliminar');
Route::get('/paseos/edicion/{id}/{salida}/{llegada}', '\App\Http\Controllers\PagesController@editar');
//Route POST




