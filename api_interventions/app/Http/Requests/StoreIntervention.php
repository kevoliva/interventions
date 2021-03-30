<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class StoreIntervention extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     *
     * @return bool
     */
    public function authorize()
    {
        return false;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array
     */
    public function rules()
    {
        return [
            'technicien_id' => 'required|integer|exists:users,id',
            'nomClient' => 'required|string|max:150',
            'prenomClient' => 'required|string|max:150',
            'adresseClient' => 'required|string|max:255',
            'marqueChaudiere' => 'required|string|max:150',
            'modeleChaudiere' => 'required|string|max:150',
            'dateMiseEnService' => 'required|date',
            'dateIntervention' => 'required|date',
            'numeroSerie' => 'required|string|max:150',
            'description' => 'required',
            'tempsPasse' => 'required|integer',
        ];
    }
}
