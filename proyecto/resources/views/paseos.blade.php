<!DOCTYPE html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Paseos</title>
    </head>
    <body>
        <h1>Paseos</h1>
        <div>
            @foreach ($paseos as $paseo)
                <p>{{ $paseo->nombre }}</p>
            @endforeach
        </div>
    </body>
</html>
