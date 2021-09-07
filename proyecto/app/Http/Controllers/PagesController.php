<?php

namespace App\Http\Controllers;

use GuzzleHttp\Client;
use Illuminate\Http\Request;

class PagesController extends Controller
{
    private $cliente;

    public function __construct(){
        $this->cliente = new Client(['base_uri' => 'http://localhost:8081/myapp/myresource/']);
    }

    public function index(){
        $respuesta = $this->cliente->get('paseos');
        $cuerpo = $respuesta->getBody();

        $data = json_decode($cuerpo);
        $paseos = array();
        foreach ($data as $obj) {         
            array_push($paseos,$obj);
        }

        return view ('paseos', ['paseos' => $paseos]);
    }

    public function eliminar($id){
        $this->cliente = new Client(['base_uri' => 'http://localhost:8081/myapp/myresource/']);
        $cuerpo = $this->cliente->delete('paseos/'.$id);
        $respuesta = $cuerpo->getBody();
        return view('productoEliminado',['respuesta' => $respuesta]);
        
    }

    

}
