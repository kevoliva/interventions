<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Intervention extends Model
{
    use HasFactory;

    protected $fillable = [
    'user_id', 'nomClient', 'prenomClient', 'adresseClient',
    'marqueChaudiere', 'modeleChaudiere', 'dateMiseEnService',
    'dateIntervention', 'numeroSerie', 'description', 'tempsPasse'
];

public function users()
{
    return $this->belongsTo(User::class, 'user_id');
}
}
