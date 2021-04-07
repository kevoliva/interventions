<?php

namespace App\Models;

use Illuminate\Contracts\Auth\MustVerifyEmail;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Notifications\Notifiable;

class User extends Authenticatable
{
  use HasFactory, Notifiable;

  /**
  * The attributes that are mass assignable.
  *
  * @var array
  */
  protected $fillable = [
    'name',
    'email',
    'password',
  ];

  protected $with = ['interventions'];

  public function interventions()
  {
    return $this->hasMany(Intervention::class);
  }

// Cette partie est commentée pour pouvoir récupérer le mot de passe sur Android : MAUVAISE PRATIQUE, NON SECURISE

  // /**
  // * The attributes that should be hidden for arrays.
  // *
  // * @var array
  // */
  // protected $hidden = [
  //   'password',
  //   'remember_token',
  // ];

  /**
  * The attributes that should be cast to native types.
  *
  * @var array
  */
  protected $casts = [
    'email_verified_at' => 'datetime',
  ];
}
