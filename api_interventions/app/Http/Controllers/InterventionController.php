<?php

namespace App\Http\Controllers;

use App\Models\Intervention;
use Illuminate\Http\Request;
use App\Http\Resources\InterventionResource;
use App\Http\Requests\StoreIntervention;

class InterventionController extends Controller
{

  public function __construct()
  {
    // rate limiting
    $this->middleware('throttle:60,1');
    $this->middleware(\Fruitcake\Cors\HandleCors::class);
  }

  /**
  * Display a listing of the resource.
  *
  * @return \Illuminate\Http\Response
  */
  public function index()
  {
    return InterventionResource::collection(Intervention::all());
  }

  /**
  * Store a newly created resource in storage.
  *
  * @param  StoreIntervention  $request
  * @return \Illuminate\Http\Response
  */
  public function store(StoreIntervention $request)
  {
    $validated = $request->validated();
    Intervention::create($validated);
  }

  /**
  * Display the specified resource.
  *
  * @param  \App\Models\Intervention  $intervention
  * @return \Illuminate\Http\Response
  */
  public function show(Intervention $intervention)
  {
    return new InterventionResource($intervention);
  }

  /**
  * Update the specified resource in storage.
  *
  * @param  \Illuminate\Http\Request  $request
  * @param  \App\Models\Intervention  $intervention
  * @return \Illuminate\Http\Response
  */
  public function update(Request $request, Intervention $intervention)
  {
    //
  }

  /**
  * Remove the specified resource from storage.
  *
  * @param  \App\Models\Intervention  $intervention
  * @return \Illuminate\Http\Response
  */
  public function destroy(Intervention $intervention)
  {
    //
  }
}
